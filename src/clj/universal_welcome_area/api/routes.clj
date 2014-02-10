(ns universal-welcome-area.api.routes
  (:require [clojure.pprint :refer [pprint]]
            [compojure.core :refer [ANY defroutes]]
            [liberator.core :refer [resource]]
            [universal-welcome-area.api.photo-upload :as u-photo-upload]
            [universal-welcome-area.utils :as u-utils]))

(defroutes api-routes
  (ANY "/api/" [] (resource :available-media-types ["text/html"]
                            :handle-ok (str "This is the API endpoint.")))
  (ANY "/api/photo-upload/" [] (resource :allowed-methods [:post]
                                         :available-media-types["multipart/form-data"]
                                         :post! (fn [ctx]
                                                  (dosync
                                                    (u-photo-upload/handle-upload ctx))))))
