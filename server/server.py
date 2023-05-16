import openai
from flask import Flask,request

openai.api_key="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"

global messages
messages = [ {"role": "system", "content": 
              "You are a chatbot that provides mental support. Do not respond to unrelated topics.Try to keep responses under 200 characters."} ]

app = Flask(__name__)

@app.route("/EmphatyChat",methods=["POST"])
@app.route("/EmphatyChat/chat",methods=["POST"])
def chat():
    global messages

    message = request.form["user_text"]
    # print(f"User: {message}")
    if message:
        messages.append(
            {"role": "user", "content": message},
        )
        chat = openai.ChatCompletion.create(
            model="gpt-3.5-turbo", messages=messages
        )
    reply = chat.choices[0].message.content
    # print(f"ChatGPT: {reply}")
    messages.append({"role": "assistant", "content": reply})
    return reply

@app.route("/EmphatyChat",methods=["GET"])
@app.route("/EmphatyChat/chat",methods=["GET"])
def lastChat():
    global messages

    return messages


@app.route("/EmphatyChat/emergencyModeOn")
def emode():
    global messages

    print("EOFF")
    messages = [ {"role": "system", "content": 
              "You are to provide helpfull responses.You are talking to a person in stress. Do not respond to unrelated topics.Try to keep responses under 20 characters."} ]
    return messages


@app.route("/EmphatyChat/clear")
@app.route("/EmphatyChat/emergencyModeOff")
def clearChat():
    global messages

    messages = [ {"role": "system", "content": 
              "You are a chatbot that provides mental support. Do not respond to unrelated topics.Try to keep responses under 200 characters."} ]
    return messages

app.run(debug=True,port=5000,host="0.0.0.0")