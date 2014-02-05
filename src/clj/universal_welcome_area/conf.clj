(ns universal-welcome-area.conf
  (:require [clojure.edn :as edn])
  (:import (java.io File)))

(def default-config-path "src/config/")

(defn throw-file-missing
  [path]
  (throw (ex-info
          (format "File missing at (%s)" path)
          {:cause :file-missing :file-path path})))

(defn file-exists?
  [path]
  (if (.isFile (File. path))
    true
    false))

(defn load-edn
  [path]
  (if (file-exists? path)
    (edn/read-string (slurp path))
    (throw-file-missing path)))

(defn load-config
  []
  (load-edn (str default-config-path "config.edn")))
