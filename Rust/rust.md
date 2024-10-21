# Introduction to Rust

## What is Rust

Rust is a modern systems programming language focused on safety, performance, and concurrency. Created by Mozilla, Rust has gained popularity due to its powerful features that allow developers to write safe and efficient code. Unlike other systems languages like C and C++, Rust is designed with memory safety in mind, reducing common bugs and vulnerabilities.

## Why use Rust over C or C++

* **Memory Safety:** Rust eliminates entire classes of bugs, such as null pointer dereferencing and buffer overflows, by enforcing strict memory safety rules.
* **Concurrencuy:** Rust's ownership model and type system prevent data races at compile time, making concurrent programming safer.
* **Performance:** Rust offers performance comparable to C and C++, with zero-cost abstractions that don't sacrifice efficiency.
* **Modern Syntax and Tooling:** Rust's syntax is clean and expressive, and it comes with powerful tools like Cargo, the package manager and build system.

## What Makes Rust Special

## The Borrowchecker

One of the key features that sets Rust apart is its borrow checker, a component of the compiler that enforces Rust’s ownership rules. This ensures that references to data do not outlive the data itself, preventing dangling pointers and data races.

# How the Borrow Checker Works

* **Ownership:** In Rust, each value has a single owner, and when the owner goes out of scope, the value is dropped.
* **Borrowing:** Rust allows references to data without taking ownership, known as borrowing. There are two types of borrowing:
    * **Immutable Borrowing:** Multiple references are allowed, but the data cannot be modified.
    * **Mutable Borrowing:** Only one mutable reference is allowed at a time, ensuring no simultaneous conflicting accesses.

This strict control over ownership and borrowing allows Rust to guarantee memory safety without a garbage collector.

# Variables in Rust

In Rust, variables are immutable by default, meaning once a value is assigned, it cannot be changed. This promotes safer code by preventing unintended side effects. However, if you need a mutable variable, you can declare it with the `mut` keyword.

```rust
fn main() {
    let x = 5;       // Immutable variable
    // x = 6;        // Error: cannot assign twice to an immutable variable
    
    let mut y = 10;  // Mutable variable
    y = 15;          // Allowed: y is mutable
    println!("y is now {}", y);
}
```

# Functions in Rust

Functions are a core building block in Rust, used to encapsulate logic and make code more modular and reusable. Rust's function syntax is straightforward, but it comes with powerful features such as type inference, pattern matching, and the ability to return values.

## Declaring Functions

In Rust, functions are declared using the `fn` keyword, followed by the function name, a list of parameters, and the return type (if any). The function body is enclosed in curly braces `{}`.

### Basic Example

```rust
fn main() {
    greet("Michael");
}

fn greet(name: &str) {
    println!("Hello, {}!", name);
}
```

* **Explanation:** In this example, the `greet` function takes a single parameter of type `&str` (a string slice) and prints a greeting message.

## Parameters and Return Types

Parameters in Rust functions must be explicitly typed. The return type is specified after the `->` symbol. If the function does not return a value, the return type is omitted.

### Basic Example

```rust
fn main() {
    let result = add(5, 3);
    println!("5 + 3 = {}", result);
}

fn add(x: i32, y: i32) -> i32 {
    x + y  // The last expression is returned by default
}
```

* **Explanation:** The `add` function takes two `i32` parameters and returns their sum. In Rust, the last expression in a function is automatically returned if it is not followed by a semicolon. You can also use the `return` keyword explicitly, but it's usually not needed.

# Structs and Enums in Rust

# Structs

Structs in Rust are used to create custom data types that group together related values. They are similar to classes in object-oriented languages but without inheritance.

### Basic Example

```rust
struct User {
    username: String,
    email: String,
    sign_in_count: u64,
    active: bool,
}

fn main() {
    let user1 = User {
        username: String::from("user1"),
        email: String::from("user1@example.com"),
        sign_in_count: 1,
        active: true,
    };

    println!("Username: {}", user1.username);
}
```

## Enums

Enums allow you to define a type by enumerating its possible variants. Each variant can optionally carry data.

### Basic Example

```rust
enum Message {
    Quit,
    Move { x: i32, y: i32 },
    Write(String),
    ChangeColor(i32, i32, i32),
}

fn main() {
    let msg = Message::Move { x: 10, y: 20 };

    match msg {
        Message::Quit => println!("Quit"),
        Message::Move { x, y } => println!("Move to ({}, {})", x, y),
        Message::Write(text) => println!("Write: {}", text),
        Message::ChangeColor(r, g, b) => println!("Change color to ({}, {}, {})", r, g, b),
    }
}
```

# Common Rust Collections

## Vec

Vectors (`Vec<T>`) are resizable arrays, one of the most common collections in Rust. They store elements contiguously in memory and can grow dynamically.

### Basic Example

```rust
fn main() {
    let mut v = Vec::new();
    v.push(1);
    v.push(2);
    v.push(3);

    for i in &v {
        println!("{}", i);
    }
}
```

## HashMap

`HashMap<K, V>` is a collection that associates keys with values. It provides fast lookup, addition, and removal of elements.

### Basic Example

```rust
use std::collections::HashMap;

fn main() {
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);

    let team_name = String::from("Blue");
    let score = scores.get(&team_name).copied().unwrap_or(0);
    println!("Score for Blue: {}", score);
}
```

# Common Rust Patterns: `Option` and `Result`

Rust’s `Option` and `Result` enums are used extensively for error handling, promoting safe code by requiring developers to handle potential absence of values or errors explicitly.

## Option

`Option<T>` is used when a value may or may not be present. It can be `Some(T)` if a value exists, or `None` if it does not.

### Basic Example

```rust
fn divide(numerator: f64, denominator: f64) -> Option<f64> {
    if denominator == 0.0 {
        None
    } else {
        Some(numerator / denominator)
    }
}

fn main() {
    let result = divide(10.0, 2.0);
    match result {
        Some(value) => println!("Result: {}", value),
        None => println!("Cannot divide by zero"),
    }
}
```

## Result

`Result<T, E>` is used for functions that can return either a success (`Ok(T)`) or an error (`Err(E)`).

### Basic Example

```rust
use std::fs::File;
use std::io::{self, Read};

fn read_username_from_file() -> Result<String, io::Error> {
    let mut file = File::open("username.txt")?;
    let mut username = String::new();
    file.read_to_string(&mut username)?;
    Ok(username)
}

fn main() {
    match read_username_from_file() {
        Ok(username) => println!("Username: {}", username),
        Err(e) => println!("Error reading file: {}", e),
    }
}
```