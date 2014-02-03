(ns universal-welcome-area.api.routes
  (:require [compojure.core :refer [defroutes ANY]]
            [liberator.core :refer [resource]]))

(defroutes api-routes
  (ANY "/api/" [] (resource :available-media-types ["text/html"]
                            :handle-ok (str "This is the API endpoint."))))
