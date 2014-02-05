(ns universal-welcome-area.utils)

(defmacro maybe-substitute
  "https://github.com/swannodette/enlive-tutorial/"
  ([expr] `(if-let [x# ~expr] (html/substitute x#) identity))
  ([expr & exprs] `(maybe-substitute (or ~expr ~@exprs))))

(defmacro maybe-content
  "https://github.com/swannodette/enlive-tutorial/"
  ([expr] `(if-let [x# ~expr] (html/content x#) identity))
  ([expr & exprs] `(maybe-content (or ~expr ~@exprs))))

(defn base-url [ctx]
  (str (get-in ctx [:request :base-url])))
