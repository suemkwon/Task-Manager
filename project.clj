(defproject task-manager "0.1.0-SNAPSHOT"
  :description "Simple Task Manager API"
  :dependencies [[org.clojure/clojure "1.11.1"]
                [compojure "1.7.0"]
                [ring/ring-defaults "0.3.4"]
                [ring/ring-json "0.5.1"]
                [org.clojure/data.json "2.4.0"]
                [ring/ring-jetty-adapter "1.9.6"]]
  :main task-manager.core
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]]}}
  :plugins [[lein-ring "0.12.6"]]
  :ring {:handler task-manager.core/app})
