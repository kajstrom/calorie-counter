(ns calorie-counter.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [calorie-counter.core-test]))

(doo-tests 'calorie-counter.core-test)

