(ns ^:figwheel-hooks mla.radicals
  (:require
   [clojure.string :as str]
   [goog.dom :as gdom]
   [hoplon.core  :as h]
   [hoplon.goog]
   [mla.csv :as csv]
   [mla.dom-utils :refer [log reset-content create-element add-class remove-class]]))

(defn multiply [a b] (* a b))

(def radicals (csv/parse csv/sample))
(def radical (first radicals))

(defn h-interpose [sep coll]
  (map (fn [f] (f))
       (interpose
        sep (map (fn [e] (fn [] e)) coll))))

(defn meaning->hoplon-elm [meaning]
  (let [words (map str/trim (str/split meaning ","))]
    (h-interpose h/br
                 (cons
                  (h/span :class "main" (first words))
                  (map (fn [word]
                         (h/span :class "alternative" word))
                       (rest words))))
    ;; (interpose h/br
    ;;            (cons
    ;;             (h/span :class "main" (first words))
    ;;             (map (fn [word]
    ;;                    (h/span :class "alternative" word))
    ;;                  (rest words))))
    ))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn create-flashcard [radical]

  (h/div
   :class "flashcard radical"
   [(h/h1
     [(h/span :class "simplified" (first (:simplified radical)))
      (h/span :class "traditional" (first (:traditional radical)))])
    (h/p :class "pyniyn" (. js/PinyinConverter convert (:pyniyn radical)))
    (h/p :class "meaning" (meaning->hoplon-elm (:meaning radical)))
    (h/p :class "variants" (:variants radical))
    (h/p :class "comment" (:comment radical))]))

(defn canvas [holder]
  (let [canvas (create-element "canvas")]
    (gdom/appendChild holder canvas)
    canvas)
  )

(def c (canvas (get-app-element)))

(defn text->img [canvas txt]
  (let [ctx (gdom/getCanvasContext2D canvas)
        dim (ctx/measureText txt)]
    (set! (.-width canvas) (.-width dim))
    (set! (.-font ctx) "16px serif")
    (.fillText ctx txt, 0, 32)
    (.toDataURL canvas)))

(defn same-chars [canvas s t]
  (= (text->img canvas s) (text->img canvas t)))


(defn create-flashcards [^web/Element holder]
  (let [elm (-> (create-element "div")
                (add-class "flashcards"))]
    (.replaceChildren holder (create-flashcard (first radicals)))))

(defn mount-components []
  (.forEach ^web/NodeList (.querySelectorAll js/document ".flashcards-holder")
            (fn [holder]
              (reset-content holder)
              (create-flashcards holder))))

(defn reload []
  (js/console.log "Reloading...")
  (mount-components))

(defn start []
  (js/console.log "Starting...")
  (mount-components))

(defn stop []
  (js/console.log "Stopping..."))

(defn init []
  (js/console.log "Initializing...")
  (start))

(init)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (reload))
