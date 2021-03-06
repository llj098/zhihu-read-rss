(ns zhihu-read-rss.core
  (:require [cheshire.core :refer :all])
  (:require [clj-http.client :as http])
  (:gen-class))

(use 'clojure.java.io)
(require '[clj-rss.core :as rss])


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn parse [x] 
  (hash-map 
   :title (second (nth x 7)) 
   :author (if (vector? (nth x 6))
             (first (nth x 6))
             "匿名用户")
   :description (nth x 2)
   :link (str "http://www.zhihu.com/question/" (nth (nth x 7) 3) "/answer/" (nth x 5))))

(defn gen-rss [json] 
  (apply rss/channel-xml 
         {:title "知乎阅读" :link "http://www.zhihu.com/read" :description "知乎阅读"} 
         (map parse (parse-string json))))

(defn get-content [page]
  (let [body (:body (http/get (str "http://zhihu.com/reader/json/" page)))]
    (gen-rss body)))

(defn save-file [path content]
  (with-open [wrtr (writer path)]
    (.write wrtr content)))

(defn -main[x]
  (try 
    (let [s (get-content 1)]
      (do
        (save-file x s)
        (println 1)))
    (catch Exception ex (println ex))))