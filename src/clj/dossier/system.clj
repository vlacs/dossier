(ns dossier.system
  (:require [org.httpkit.server :refer :all]
            [dossier.conf :as d-conf]
            [dossier.core :as d-core]))

(defn system []
  d-conf/load-config)

(defonce server (atom nil))

(defn start-http [system]
  (reset! server (run-server d-core/app {:port (get-in (system) [:web :port])})))

(defn stop-http []
  (when-not (nil? @server)
    ;;Wait 100ms for existing requests to be finished
    (@server :timeout 100)
    (reset! server nil)))

(defn start [system]
  (start-http system))

(defn stop [system]
  (stop-http))

(defn -main [&args]
  (start system))
