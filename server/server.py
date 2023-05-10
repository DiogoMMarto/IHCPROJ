import openai
from flask import Flask,request

openai.api_key="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"

messages = [ {"role": "system", "content": 
              "You are a chatbot that provides mental support. Do not respond to unrelated topics.Try to keep responses under 200 characters."} ]

app = Flask(__name__)

@app.route("/EmphatyChat",methods=["POST"])
def chat():
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
def lastChat():
    return messages

@app.route("/EmphatyChat/clear")
def clearChat():
    messages = [ {"role": "system", "content": 
              "You are a chatbot that provides mental support. Do not respond to unrelated topics.Try to keep responses under 200 characters."} ]
    return messages

app.run(debug=True,port=5000,host="0.0.0.0")