(ns dossier.api.photo-upload
  (:require [cheshire.core :refer [generate-string]]
            [clojure.pprint :refer [pprint]]
            [noir.io :refer [create-path upload-file]]
            [dossier.api.api :refer [gen-response]]
            [dossier.validation :refer [invalid-image?]]))

(def upload-path "resources/public/upload/photo-upload")

(defn handle-upload [ctx]
  (let [photo (get-in ctx [:request :multipart-params "photo"])]
    (cond
     (or (= (:size photo) 0) (nil? photo)) (gen-response "Whoa! The whole point of this form is to upload an image. You didn't even choose one...")
     (invalid-image? photo) (gen-response "Whoa! We only accept png/jpg/jpeg/gif images!")
     :else (try
             (upload-file upload-path photo :create-path? true)
             (gen-response)))))
