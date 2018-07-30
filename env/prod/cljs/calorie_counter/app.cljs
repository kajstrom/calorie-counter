(ns calorie-counter.app
  (:require [calorie-counter.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
