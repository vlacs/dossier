(ns uwa.conf
  (:require [clojure.edn :as edn])
  (:import [java.io File]))

(def default-config-path "config")

(def config-associations {:http-instance [:conf-web]
                          :datomic-db [:conf-datomic-db]})

(defn determine-path
  [path]
  (if path
    path
    default-config-path))

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

(defn find-required-configs
  [sub-system-list]
  (flatten (vals (select-keys config-associations sub-system-list))))

(defn make-keyed-config
  [path k]
  (let [file-name (get default-configs k)]
    [k (load-edn (str path "/" file-name))]))

(defn load-configs
  ([path] (load-configs path (keys config-associations)))
  ([path sub-system-list]
   (let [required-configs (find-required-configs sub-system-list)
         handler-fn (partial make-keyed-config (determine-path path))]
     (prn required-configs)
     (apply hash-map (flatten (map handler-fn required-configs)))))
