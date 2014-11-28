(defproject io.torsten/hexcompress "0.0.2"
  :description "Effective compression for short ASCII strings with many (hex) numbers."
  :url "https://github.com/torsten/hexcompress/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :aliases {"test" ["midje"]
            "all" ["with-profile" "dev,1.6:dev,1.5"]}
  :profiles {:1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.0.0"]]}})
