(ns uwa.system
  (:use org.httpkit.server))

(defn system
  "Returns a new instance of the entire applicaiton"
  [conf]
  {:conf conf})

(defonce server (atom nil))

(defn start-http []
  (reset! server (run-server #'core.app {:port 8080})))

(defn stop-http []
  (when-not (nil? @server)
    ;;Wait 100ms for existing requests to be finished
    (@server :timeout 100)
    (reset! server nil)))

(defn start
  [system]
  (start-http))

(defn -main [&args]
  (start system))
