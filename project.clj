(defproject org.clojars.fabuime/feline "0.1.0-SNAPSHOT"
  :description "Two functions fmap and nmap can transform nested collections or maps"
  :url "https://github.com/fabianmurariu/feline"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.227"]]

  :plugins [[lein-cljsbuild "1.1.4"]]

  :hooks [leiningen.cljsbuild]

  :cljsbuild {:builds
              {:min {:source-paths ["src"]
                     :compiler {:output-to "out/main.js"
                                :optimizations :advanced}}}}
  )
