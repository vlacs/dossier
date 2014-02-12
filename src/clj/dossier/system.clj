(ns dossier.system
  (:require [org.httpkit.server :refer :all]
            [dossier.conf :as u-conf]
            [dossier.core :as u-core]))

(defn system []
  u-conf/load-config)

(defonce server (atom nil))

(defn start-http [system]
  (reset! server (run-server u-core/app {:port (get-in (system) [:web :port])})))

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
