(ns calorie-counter.components.auth
  (:require [reagent.core :as r]
            [struct.core :as st]
            [calorie-counter.validation :refer [registration-scheme]]
            [calorie-counter.il8n :refer [trs]]
            [calorie-counter.session :refer [session redirect-to-hash!]]
            [calorie-counter.components.notification :refer [add-notification]]
            [ajax.core :refer [POST]]))

(defn register! [fields errors]
  (POST "/api/users"
        {:params @fields
         :handler #(do
                    (reset! fields {})
                    (reset! errors nil)
                    (add-notification (trs [:registration-success-notification]) :success)
                    (redirect-to-hash! ""))
         :error-handler #(do
                          (println %)
                          (reset! errors (-> % :response :errors)))}))

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
       (form-input fields errors (trs [:password]) "" :password :props {:type "password"})
       (form-input fields errors (trs [:first_name]) "" :first_name)
       (form-input fields errors (trs [:last_name]) "" :last_name)
       [:button.btn.btn-primary {:on-click #(do
                                             (if-let [err-msgs (first (st/validate @fields registration-scheme))]
                                               (reset! errors err-msgs)
                                               (register! fields errors))
                                             )} (trs [:submit])]])))

(defn login-form []
  (let [fields (r/atom {})
        errors (r/atom nil)]
    (fn []
      [:div
       [:h1 "Login"]
       (form-input fields errors (trs [:email]) "" :email)
       (form-input fields errors (trs [:password]) "" :password :props {:type "password"})
       [:button.btn.btn-primary (trs [:submit])]
       ])))