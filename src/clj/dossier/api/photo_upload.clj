(ns dossier.api.photo-upload
  (:require [clojure.pprint :refer [pprint]]
            [noir.io :refer [create-path upload-file]]
            [dossier.validation :refer [invalid-image?]]))

(def upload-path "resources/public/upload/photo-upload")

(defn handle-upload [ctx]
  (let [photo (get-in ctx [:request :multipart-params "photo"])]
    (cond
     (= (:size photo) 0) {:request-error "Whoa! The whole point of this form is to upload an image. You didn't even choose one..."}
     (invalid-image? photo) {:request-error "Whoa! We only accept png/jpg/jpeg/gif images!"}
     :else (try
             (upload-file upload-path photo :create-path? true)))))
