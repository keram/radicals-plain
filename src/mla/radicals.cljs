(ns ^:figwheel-hooks mla.radicals
  (:require
   [clojure.string :as str]
   [goog.dom :as gdom]
   [mla.csv :as csv]))

(println "This -t-")

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
(defonce app-state (atom {:text "Hello world!"}))

(defn get-app-element []
  (gdom/getElement "app"))


;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
