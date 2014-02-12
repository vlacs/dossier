(ns universal-welcome-area.api.photo-upload
  (:require [clojure.pprint :refer [pprint]]
            [noir.io :refer [create-path upload-file]]))

(def upload-path "photo-upload")

(defn handle-upload [ctx]
  (let [photo (get-in ctx [:request :multipart-params "photo"])]
    (pprint photo)
    (try
      (upload-file upload-path photo :create-path? true))))
