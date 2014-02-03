(ns universal-welcome-area.system
  (:use org.httpkit.server)
  (:require [universal-welcome-area.conf :as conf]
            [universal-welcome-area.core :as core]))

(defn system
  []
  (let [conf (conf/load-config)]
  {:conf conf}))

(defonce server (atom nil))

(defn start-http []
  (reset! server (run-server core/app {:port 8081})))

(defn stop-http []
  (when-not (nil? @server)
    ;;Wait 100ms for existing requests to be finished
    (@server :timeout 100)
    (reset! server nil)))

(defn start [_]
  (start-http))

(defn stop [_]
  (stop-http))

(defn -main [&args]
  (start system))
