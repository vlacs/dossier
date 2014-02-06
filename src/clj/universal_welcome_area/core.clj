(ns universal-welcome-area.core
  (:require [compojure.core :refer [ANY defroutes routes]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [not-found resources]]
            [liberator.core :refer [resource]]
            [universal-welcome-area.api.routes :refer [api-routes]]
            [universal-welcome-area.templates :as tmpl]
            [universal-welcome-area.utils :as u-utils]
            [universal-welcome-area.web.http :refer [wrap-host-urls]]))

(defroutes main-routes
  (ANY "/" [] (resource :available-media-types ["text/html"]
                        :handle-ok (fn [ctx]
                                     (tmpl/render (tmpl/view_main (u-utils/base-url ctx)))))))

(def handler
  (routes
   (resources "/")
   (site main-routes)
   (site api-routes)
   (not-found "The resource you are looking for is not here.")))

(def app (-> (var handler)
             (wrap-host-urls)))
