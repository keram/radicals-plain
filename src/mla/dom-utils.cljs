(ns mla.csv)

(defn log [& args]
  (.log js/console (clj->js args)))

(defn create-element [name]
  (goog.dom/createElement name))

(defn add-class [^js/Element elm cls]
  (.add (.-classList elm) cls)
  elm)

(defn remove-class [^js/Element elm cls]
  (.remove (.-classList elm) cls)
  elm)

(defn reset-content [^js/Element elm]
  (set! (.-innerHTML elm) ""))

(defn get-style [^js/Element elm property]
  (.getPropertyValue ^js/CSSStyleDeclaration (js/getComputedStyle elm) property))

(defn set-style [^js/Element elm style]
  (doseq [n style
          :let [s ^js/CSSStyleDeclaration (.-style elm)]]
    (.setProperty s (first n) (last n)))
  elm)

(defn set-dim [^js/HTMLElement elm width height]
  (set-style elm {"width" (str width "px")
                  "height" (str height "px")})
  elm)
