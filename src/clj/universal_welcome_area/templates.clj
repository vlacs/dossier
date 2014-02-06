(ns universal-welcome-area.templates
  (:require [net.cgrand.enlive-html :as html]
            [universal-welcome-area.utils :refer [maybe-content
                                                  maybe-substitute]]))

(defn replace-asset-path [base-url]
  (html/transform-content (html/replace-vars {:ASSET_PATH (str base-url "/static/")})))

(defn render [template]
  (reduce str template))

;; base template

(html/deftemplate base "templates/base.html"
  [{:keys [title nav content footer base-url]}]
  [:#title]        (maybe-content title)
  [:#nav]          (maybe-substitute nav)
  [:#content]      (maybe-substitute content)
  [:#footer]       (maybe-substitute footer)
  [#{:head :body}] (replace-asset-path base-url))

;;snippets

(html/defsnippet nav "templates/snippets/nav.html" [:div#nav]
  [{:keys [brand nav-items]}]
  [:a#brand] (maybe-content brand))

(html/defsnippet footer "templates/snippets/footer.html" [:footer#footer]
  [])

;;pages

(html/defsnippet main "templates/pages/main.html" [:div#content]
  [])

;;layouts

(defn layout-main [title content base-url]
  (base {:title title
         :nav (nav {:brand "Universal Welcome Area"})
         :content content
         :footer (footer)
         :base-url base-url}))

;;views

(defn view-main [base-url]
  (layout-main "VLACS UWA" (main) base-url))

