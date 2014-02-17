(ns dossier.api.routes
  (:require [cheshire.core :refer [generate-string]]
            [clojure.pprint :refer [pprint]]
            [compojure.core :refer [ANY defroutes]]
            [liberator.core :refer [resource]]
            [dossier.api.api :as d-api]
            [dossier.api.photo-upload :as d-photo-upload]
            [dossier.utils :as d-utils]))

(defroutes api-routes
  (ANY "/api/" [] (resource :available-media-types ["text/html"]
                            :handle-ok (str "This is the API endpoint.")))
  (ANY "/api/photo-upload/" [] (resource :allowed-methods [:post :put]
                                         :available-media-types["application-json"]
                                         :post! (fn [ctx] (dosync (d-photo-upload/handle-upload ctx)))
                                         :post-redirect? (fn [ctx] (d-api/post-has-error? ctx))
                                         :put! (fn [ctx] (dosync (d-photo-upload/handle-upload ctx)))
                                         :handle-created (fn [ctx] (d-api/handle-created ctx)))))
