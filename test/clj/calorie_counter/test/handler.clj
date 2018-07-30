(ns calorie-counter.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [calorie-counter.handler :refer :all]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'calorie-counter.config/env
                 #'calorie-counter.handler/app)
    (f)))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response))))))
