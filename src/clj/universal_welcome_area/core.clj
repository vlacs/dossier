(ns universal-welcome-area.core
  (:require [clojure.pprint :refer [pprint]]
            [compojure.core :refer [ANY defroutes routes]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [not-found resources]]
            [liberator.core :refer [resource]]
            [liberator.dev :refer [wrap-trace]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [universal-welcome-area.api.routes :refer [api-routes]]
            [universal-welcome-area.templates :as tmpl]
            [universal-welcome-area.utils :as u-utils]
            [universal-welcome-area.web.http :refer [wrap-host-urls]]))

(defroutes main-routes
  (ANY "/" [] (resource :available-media-types ["text/html"]
                        :handle-ok (fn [ctx]
                                     (tmpl/render (tmpl/view-main ctx)))))
  (ANY "/upload/" [] (resource :available-media-types["text/html"]
                               :handle-ok (fn [ctx]
                                            (tmpl/render (tmpl/view-test ctx))))))

(def handler
  (routes
   (resources "/")
   (site main-routes)
   (site api-routes)
   (not-found "The resource you are looking for is not here.")))

(def app (-> (var handler)
             (wrap-trace :header :ui)
             (wrap-params)
             (wrap-multipart-params)
             (wrap-file-info)
             (wrap-host-urls)))
