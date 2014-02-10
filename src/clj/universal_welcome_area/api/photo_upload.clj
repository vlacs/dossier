(ns universal-welcome-area.api.photo-upload
  (:require [clojure.pprint :refer [pprint]])
  (:import [java.io File FileInputStream]
           [java.awt.image AffineTransformOp BufferedImage]
           java.awt.RenderingHints
           java.awt.geom.AffineTransform
           javax.imageio.ImageIO))

(defn handle-upload [ctx]
  (pprint (get-in ctx [:request :multipart-params]))
  false)
