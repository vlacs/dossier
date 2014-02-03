(ns universal-welcome-area.templates
  (:require [net.cgrand.enlive-html :as html])
  (:use [universal-welcome-area.utils
         :only [maybe-content maybe-substitute]]))

(defn replace-asset-path []
  (html/transform-content (html/replace-vars {:ASSET_PATH "//localhost:8081/static/"})))

(html/deftemplate base "templates/base.html"
  [{:keys [title nav content footer]}]
  [:#title]  (maybe-content title)
  [:#nav] (maybe-substitute nav)
  [:#content] (maybe-substitute content)
  [:#footer] (maybe-substitute footer)
  [#{:head :body}] (replace-asset-path))
