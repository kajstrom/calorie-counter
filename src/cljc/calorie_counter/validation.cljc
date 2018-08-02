(ns calorie-counter.validation
  (:require [struct.core :as st]
            [calorie-counter.il8n :refer [trs]]))

(def registration-scheme
  {:email [[st/required :message (trs [:validation/required-field])]
           [st/email :message (trs [:validation/invalid-email])]
           [st/max-count 300 :message (trs [:validation/max-length] [300])]]
   :password [[st/required :message (trs [:validation/required-field])]]
   :first_name [[st/required :message (trs [:validation/required-field])]
                [st/max-count 50 :message (trs [:validation/max-length [50]])]]
   :last_name [[st/required :message (trs [:validation/required-field])]
               [st/max-count 50 :message (trs [:validation/max-length] [50])]]})
