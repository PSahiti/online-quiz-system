# Online Quiz System

A menu-driven console quiz application written in **Java**, built as a beginner-level
**Data Structures and Algorithms (DSA)** project.

**Student:** Pakalapati Sahithi  
**ERP ID:** RU-25-10939  
**Program:** B.Tech Computer Science & Engineering with Artificial Intelligence and Machine Learning, in association with Google  
**Guide:** Ms. Naina Singh  
**University:** School of Computer Science and Engineering, Rungta International Skills University, Bhilai, Chhattisgarh  
**Course:** RU-200-01-00029 – Data Structures and Algorithms (Session 2026-27)

## Data structures used

| Concept | Where it is used |
|---|---|
| **Array** | `Question[]` question bank, 4 options per question, leaderboard `ScoreEntry[]` |
| **Queue (FIFO)** | Questions are asked one by one in the order they were added |
| **Stack (LIFO)** | Wrong answers are pushed and reviewed in reverse order at the end |
| **Selection Sort** | Sorts the leaderboard from highest to lowest score |

## Features

- 10 multiple-choice questions with 4 options each
- Instant feedback after every answer
- Score and review of mistakes (last wrong answer first)
- Leaderboard of up to 10 players per session (ties ordered alphabetically)
- Input validation (empty name becomes "Guest"; non-numeric / out-of-range answers are rejected)

## Project structure

```
online-quiz-system/
├── src/
│   ├── Main.java          # console menu, input validation, review
│   ├── QuizEngine.java    # Array + Queue + Stack logic
│   ├── Leaderboard.java   # Array + Selection Sort
│   ├── Question.java
│   └── ScoreEntry.java
├── README.md
└── .gitignore
```

## How to run

Requires **JDK 21** (JDK 11+ also works).

```bash
cd src
javac *.java
java Main
```

## Time complexity

N = questions, W = wrong answers, M = players (M ≤ 10)

| Operation | Complexity |
|---|---|
| Access a question/option by index | O(1) |
| Load all questions into the Queue | O(N) |
| Enqueue / dequeue one question | O(1) |
| Push / pop on the Stack | O(1); full review O(W) |
| Selection Sort of leaderboard | O(M²) |
| One complete quiz attempt | O(N) |
