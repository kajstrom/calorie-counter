(ns calorie-counter.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [calorie-counter.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[calorie-counter started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[calorie-counter has shut down successfully]=-"))
   :middleware wrap-dev})
