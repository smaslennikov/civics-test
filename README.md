# Civics Test 2022 app

Simple app for running through the 2022 USCIS Civics Test. Features include:

- [x] Quiz questions with written answer prompts rather than multiple choice (real interview isn't multiple choice, but all apps seem to be)
- [x] Go through the entire pool of questions
- [x] Only ask missed questions after the first iteration
- [x] Option to reset pool of known questions

The app is functional enough for me to study for my interview in three days. That said, there are some bugs left:

- [ ] Quiz Activity view issues
    - [ ] Buttons don't shrink with the soft keyboard enabled
    - [ ] Text input field has an extra line above
    - [ ] Pressing enter in the text input field should submit the answer
    - [ ] Question field should be using the screen space better (reduce button sizes)
    - [ ] Question field should be scrollable
- [ ] Some of the questions don't have any answers set because they're state-specific
- [x] Some of the questions don't have any answers set because they're time-specific (update for 2022)
- [ ] App crashes after the last question

Stretch goals:

- [ ] Display scores, store them between state resets
- [ ] Automatically pull the USCIS PDF on startup and import it
- [ ] Use localized answers for state-specific questions
- [ ] Release a generic quizzing framework

## Resources used

- [Kotlin Android Quiz App in Android Studio](https://techpassmaster.com/kotlin-android-quiz-app/)
- [Android: Build your first app](https://developer.android.com/training/basics/firstapp)

## License

Attribution-ShareAlike 4.0 International

[![](https://licensebuttons.net/l/by-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-sa/4.0/)
