(ns universal-welcome-area.api.routes
  (:require [clojure.pprint :refer [pprint]]
            [compojure.core :refer [ANY defroutes]]
            [liberator.core :refer [resource]]
            [universal-welcome-area.utils :as u-utils]))

(defroutes api-routes
  (ANY "/api/" [] (resource :available-media-types ["text/html"]
                            :handle-ok (str "This is the API endpoint.")))
  (ANY "/api/photo-upload/" [] (resource :allowed-methods [:post]
                                         :available-media-types["multipart/form-data"]
                                         :post! (fn [ctx]
                                                  (pprint ctx))
                                         :post-redirect? (fn [ctx] {:location (str (u-utils/base-url ctx) "test/")}))))
