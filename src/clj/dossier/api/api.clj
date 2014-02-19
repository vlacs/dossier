(ns dossier.api.api
  (:require [cheshire.core :refer [generate-string]]
            [clojure.pprint :refer [pprint]]
            [dossier.utils :as d-utils]))

(def response-key :response-status)

(defn gen-response
  ([] {response-key {:status "success"}})
  ([error] {response-key {:status "fail" :error error}}))

(defn handle-created [ctx]
  (let [status (get-in ctx [response-key :status])
        error  (get-in ctx [response-key :error])]
    (generate-string {:status status :error error})))

(defn post-has-error? [ctx]
  (if
    (empty? (:request-error ctx))
    {:location (d-utils/referer ctx)}
    {:location (str (d-utils/referer ctx) "?error=" (get-in ctx [response-key :error]))}))
