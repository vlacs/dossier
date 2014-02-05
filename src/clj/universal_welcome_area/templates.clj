(ns universal-welcome-area.templates
  (:require [net.cgrand.enlive-html :as html]
            [universal-welcome-area.utils :refer [maybe-content
                                                  maybe-substitute]]))

(defn replace-asset-path [base-url]
  (html/transform-content (html/replace-vars {:ASSET_PATH (str base-url "/static/")})))

(defn render [template]
  (reduce str template))

(html/deftemplate base "templates/base.html"
  [{:keys [title nav content footer base-url]}]
  [:#title]        (maybe-content title)
  [:#nav]          (maybe-substitute nav)
  [:#content]      (maybe-substitute content)
  [:#footer]       (maybe-substitute footer)
  [#{:head :body}] (replace-asset-path base-url))
