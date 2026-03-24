from flask import Flask, redirect, url_for, request, render_template, session
import requests

app = Flask(__name__)
AUTH_URL = "http://localhost:8081/auth"

@app.route('/')
def index():
    return redirect(url_for('login'))

@app.route('/login')
def login():
    error = None
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        data = {
            "username": username,
            "password": password
        }

        response = requests.post(f"{AUTH_URL}/login", json=data).json()
        if response["success"]:
            session['member_id'] = response["member_id"]
            session['username'] = response["username"]
            return redirect(url_for('dashboard'))
        else:
            error = data.get("message", "Autentificare esuata.")

    return render_template('login.html', error=error)

@app.route('/register')
def register():
    error = None
    success = None
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        firstName = request.form['firstName']
        lastName = request.form['lastName']

        data = {
            "username": username,
            "password": password,
            "firstName": firstName,
            "lastName": lastName
        }

        response = requests.post(f"{AUTH_URL}/register", json=data).json()

        if response["success"]:
            success = "Cont creat cu succes! Te poti autentifica."
        else:
            error = data.get("message", "Inregistrare esuata.")
    return render_template('register.html', error=error, success=success)

@app.route('/dashboard')
def dashboard():
    if 'member_id' not in session:
        return redirect(url_for('login'))
    return f"Bun venit, {session['username']}!"

if __name__ == '__main__':
    app.run(debug=True)