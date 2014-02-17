(defproject dossier "0.1.1-SNAPSHOT"
  :description "Streamlines the creation of classroom welcome areas."
  :url "http://vlacs.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cheshire "5.3.1"]
                 [compojure "1.1.6"]
                 [clj-aws-s3 "0.3.7"]
                 [enlive "1.1.5"]
                 [http-kit "2.1.16"]
                 [liberator "0.10.0"]
                 [lib-noir "0.8.0"]
                 [org.clojure/clojure "1.5.1"]
                 [ring "1.2.1"]]

  :source-paths ["src/clj"]
  :resource-paths ["resources"]

  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [org.clojure/java.classpath "0.2.2"]]}}
  :main ^{:skip-aot true} dossier.system)
