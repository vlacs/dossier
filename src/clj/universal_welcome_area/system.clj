(ns universal-welcome-area.system
  (:use org.httpkit.server)
  (:require [universal-welcome-area.conf :as u-conf]
            [universal-welcome-area.core :as u-core]))

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
