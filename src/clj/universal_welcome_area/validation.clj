(ns universal-welcome-area.validation)

(defn invalid-image? [image]
  (let [ct (:content-type image)]
    (cond
     (= ct "image/png") false
     (= ct "image/gif") false
     (= ct "image/jpeg") false
     :else true)))
