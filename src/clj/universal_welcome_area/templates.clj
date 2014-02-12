(ns universal-welcome-area.templates
  (:require [net.cgrand.enlive-html :as html]
            [universal-welcome-area.utils :as u-utils]
            [universal-welcome-area.utils :refer [maybe-content
                                                  maybe-substitute]]))

(defn replace-asset-path [base-url]
  (html/transform-content (html/replace-vars {:ASSET_PATH (str base-url "/static/")})))

(defn render [template]
  (reduce str template))

;; base template

(html/deftemplate base "templates/base.html"
  [{:keys [title nav content footer base-url error]}]
  [:#title]        (maybe-content title)
  [:#nav]          (maybe-substitute nav)
  [:#content]      (maybe-substitute content)
  [:#footer]       (maybe-substitute footer)
  [#{:head :body}] (replace-asset-path base-url)
  [:#error]        (maybe-substitute error))

;;snippets

(html/defsnippet nav "templates/snippets/nav.html" [:div#nav]
  [{:keys [brand nav-items]}]
  [:a#brand] (maybe-content brand))

(html/defsnippet footer "templates/snippets/footer.html" [:footer#footer]
  [])

(html/defsnippet error "templates/snippets/error.html" [:div#error]
  [error]
  [:div#error-msg] (html/transform-content (html/replace-vars {:ERROR_MSG error})))

(html/defsnippet error-hidden "templates/snippets/error-hidden.html" [:div#error]
  [])

;;pages

(html/defsnippet pg-main "templates/pages/main.html" [:div#content]
  [])

(html/defsnippet pg-test "templates/pages/test.html" [:div#content]
  [])

;;layouts

(defn layout-main [title content ctx]
  (base {:title title
         :nav (nav {:brand "Universal Welcome Area"})
         :content content
         :footer (footer)
         :base-url (u-utils/base-url ctx)
         :error (if (empty? (u-utils/error ctx)) (error-hidden) (error (u-utils/error ctx)))}))

;;views

(defn view-main [ctx]
  (layout-main "VLACS UWA" (pg-main) ctx))

(defn view-test [ctx]
  (layout-main "VLACS Upload" (pg-test) ctx))
