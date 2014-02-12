(ns dossier.api.routes
  (:require [clojure.pprint :refer [pprint]]
            [compojure.core :refer [ANY defroutes]]
            [liberator.core :refer [resource]]
            [dossier.api.photo-upload :as u-photo-upload]
            [dossier.utils :as u-utils]))

(defn post-has-error? [ctx]
  (if
    (empty? (:request-error ctx))
    {:location (u-utils/referer ctx)}
    {:location (str (u-utils/referer ctx) "?error=" (:request-error ctx))}))

(defroutes api-routes
  (ANY "/api/" [] (resource :available-media-types ["text/html"]
                            :handle-ok (str "This is the API endpoint.")))
  (ANY "/api/photo-upload/" [] (resource :allowed-methods [:post]
                                         :available-media-types["multipart/form-data"]
                                         :post! (fn [ctx] (dosync (u-photo-upload/handle-upload ctx)))
                                         :post-redirect? (fn [ctx] (post-has-error? ctx)))))
