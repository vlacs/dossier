(ns universal-welcome-area.core
  (:require [liberator.core :refer [resource]]
            [compojure.core :refer [defroutes routes ANY]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [universal-welcome-area.api.routes :refer [api-routes]]
            [universal-welcome-area.templates :as tmpl]
            [universal-welcome-area.web.http :refer [wrap-host-urls]]
            [universal-welcome-area.utils :as u-utils]))

(defroutes main-routes
  (ANY "/" [] (resource :available-media-types ["text/html"]
                        :handle-ok (fn [ctx]
                                     (tmpl/render (tmpl/base {:base-url (u-utils/base-url ctx)
                                                              :title "Universal Welcome Area!"}))))))

(def handler
  (routes
   (resources "/")
   (site main-routes)
   (site api-routes)
   (not-found "The resource you are looking for is not here.")))

(def app (-> (var handler)
             (wrap-host-urls)))
