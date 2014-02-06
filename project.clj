(defproject universal-welcome-area "0.1.0-SNAPSHOT"
  :description "Streamlines the creation of classroom welcome areas."
  :url "http://vlacs.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[compojure "1.1.6"]
                 [enlive "1.1.5"]
                 [http-kit "2.1.16"]
                 [javax.servlet/servlet-api "2.5"]
                 [liberator "0.10.0"]
                 [org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.2.1"]]

  :source-paths ["src/clj"]
  :resource-paths ["resources"]

  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.4"]
                                  [org.clojure/java.classpath "0.2.2"]]}}
  :main ^{:skip-aot true} universal-welcome-area.system)
