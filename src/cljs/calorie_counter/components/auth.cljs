(ns calorie-counter.components.auth
  (:require [reagent.core :as r]
            [struct.core :as st]
            [calorie-counter.validation :refer [registration-scheme]]
            [calorie-counter.il8n :refer [trs]]))

(defn form-input [fields errors label placeholder key & {:keys [props] :or {props {}}}]
  (let [error-msg (key @errors)
         defaults {
                    :class (if error-msg ["is-invalid"] nil)
                    :on-change #(swap! fields assoc key (-> % .-target .-value))
                    :placeholder placeholder
                    :value (key @fields)}]
    [:div.form-group
     [:label label]
     [:input.form-control (merge defaults props)]
     (when error-msg
       [:div.invalid-feedback error-msg])]))

(defn registration-form []
  (let [fields (r/atom {})
        errors (r/atom nil)]
    (fn []
      [:div
       (form-input fields errors (trs [:email]) "someone@example.org" :email)
       (form-input fields errors (trs [:password]) "" :password)
       (form-input fields errors (trs [:first_name]) "" :first_name)
       (form-input fields errors (trs [:last_name]) "" :last_name)
       [:button.btn.btn-primary {:on-click #(do
                                             (reset! errors (first (st/validate @fields registration-scheme)))
                                             (println @errors)
                                             )} (trs [:submit])]])))