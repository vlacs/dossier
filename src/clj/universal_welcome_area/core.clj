(ns universal-welcome-area.core
  (:require [liberator.core :refer [resource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [universal-welcome-area.templates :as tmpl]))

(defroutes app-routes
  (resources "/")
  (ANY "/" [] (resource :available-media-types ["text/html"]
                        :handle-ok (reduce str (tmpl/base {})))))

(def handler
  (site app-routes))

(def app (-> (var handler)
             (wrap-params)
             (site)))
