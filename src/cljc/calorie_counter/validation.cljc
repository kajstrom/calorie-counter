(ns calorie-counter.validation
  (:require [struct.core :as st]))

(def registration-scheme
  {:email [[st/required :message "Email is required"]
           [st/email :message "Invalid email"]
           [st/max-count 300]]
   :password [st/required]
   :first_name [st/required
                [st/max-count 50]]
   :last_name [st/required
               [st/max-count 50]]})
