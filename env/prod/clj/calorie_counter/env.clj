(ns calorie-counter.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[calorie-counter started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[calorie-counter has shut down successfully]=-"))
   :middleware identity})
