(ns ^:figwheel-hooks mla.radicals
  (:require
   [clojure.string :as str]
   [goog.dom :as gdom]
   [hoplon.core  :as h]
   [hoplon.goog]
   [mla.csv :as csv]
   [mla.dom-utils :refer [log reset-content create-element add-class remove-class]]
   ))

(defn multiply [a b] (* a b))

(def radicals (csv/parse csv/sample))

(defn meaning->component [meaning]
  (let [words (map str/trim (str/split meaning ","))]
    [:p.meaning
     (interpose ^{:key (str "br" (rand-int 1000))}[:br]
                (concat
                 [^{:key (str "w" (rand-int 1000))}
                  [:span.main (first words)]]
                 (map
                  (fn [word]
                    ^{:key word} [:span.alternative word])
                  (rest words))))]))

(defn radical-component [radical]
  [:div.radical
   [:h1
    [:span.simplified (first (:simplified radical))]
    [:span.traditional (first (:traditional radical))]]
   [:p.pyniyn (. js/PinyinConverter convert (:pyniyn radical))]
   (meaning->component (:meaning radical))
   [:p.variants (:variants radical)]
   [:p.comment (:comment radical)]])

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn create-flashcard [radical]
  (h/div :class "radical" "a card")
  )

(defn create-flashcards [^web/Element holder]
  (let [elm (-> (create-element "div")
                (add-class "flashcards")
                )]
    (.appendChild elm (gdom/createTextNode "foo"))
    (.appendChild holder elm)))

(defn mount-components []
  (.replaceChildren (.getElementById js/document "app")
    (create-flashcard "x")))

;; (.forEach ^web/NodeList (.querySelectorAll js/document ".flashcards-holder")
;;           (fn [elm]
;;             (reset-content elm)
;;             (create-flashcards elm)
;;             ))
;; (set! (.-innerHTML elm) "-t- aaaxxx")

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

(defn start []
  (mount-components)
  (js/console.log "Starting..."))

(defn stop []
  (js/console.log "Stopping..."))

(defn init []
  (js/console.log "Initializing...")
  (start))
