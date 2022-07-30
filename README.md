# account-touch
A-Touch is a simple account-sharing service.

## feature

### user & permission

<img src="https://user-images.githubusercontent.com/37995685/181921143-da88ec72-8fd6-4feb-8c97-d94779b117f1.png" width="600" height ="280"/>

Create an user with a nickname and emoji

### account

<img src="https://user-images.githubusercontent.com/37995685/181921152-50eb8bc9-0113-4b6f-9a29-aa33fd5d6b14.png" width="600" height ="280"/>

You can see the list of accounts, and when registering an account, select an account nickname, color, and bank to register.

### account send and receive

<img src="https://user-images.githubusercontent.com/37995685/181921195-2f2bf4d1-3ef7-494e-bf01-3a33928105de.png" width="600" height ="280"/>

When sending a registered account, click the send icon and the account will be delivered to people who are using this app nearby.

<img src="https://user-images.githubusercontent.com/37995685/181921204-58b60e49-0828-4950-9ca2-a69fbd03182d.png" width="600" height ="280"/>

When you receive an account, the records received are saved and can be viewed again.

<img src="https://user-images.githubusercontent.com/37995685/181921331-f2a076a2-e96d-4def-ab6f-2bd58a74cdee.png" width="600" height ="280"/>

You can also copy the account number or move to the appropriate bank app for that account.

## use library code
``` kotlin
//speak
txManager.euInitTransmit(FormatUtil.infoToJson(
    InfoModel(bankId, accountNumber)
))

txManager.process(-1)

//listen
rxManager.listen()

rxManager.acousticSensor = AcousticSensor {
    val received = FormatUtil.jsonToInfo(it)

    _info.value = received
    save(CreateReceivedRequest(received.id, received.num))
}

```

## develop


## Team Link
[🔎click here](https://github.com/orgs/euphony-io/teams/euphoever)
