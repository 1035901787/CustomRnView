/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  DeviceEventEmitter
} from 'react-native';
import CustomTextView from './src/CustomTextView';
import CustomToastView from './src/CustomToastView';

export default class CustomRnView extends Component {

  componentDidMount() {
    DeviceEventEmitter.addListener(CustomToastView.TestEventName,(res)=>{
        console.log("我是事件监听");
        console.log(res);
    });
  }

  _getNativeClass = (msg) => {
    CustomToastView.show(msg,CustomToastView.SHORT);
  }

  _getNativePromise = (msg) => {
    CustomToastView.getArguments(true)
        .then((res) => {
            console.log("getArguments---success");
            console.log(res);
        },(error)=>{
          console.log("getArguments---error");
          console.log(res);
        });
  }

  render() {
    return (
      <View style={styles.container}>
        <CustomTextView
            style={styles.myTextView}
            text="我是封装的原生组件"
            textSize={15}
            onChangeMessage={(msg)=>{
              CustomToastView.show("点到我了----",CustomToastView.SHORT);
              CustomToastView.getNativeClass(this._getNativeClass);
              this._getNativePromise();
            }}/>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.android.js
        </Text>
        <Text style={styles.instructions}>
          Double tap R on your keyboard to reload,{'\n'}
          Shake or press menu button for dev menu
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  myTextView:{
    width:300,
    height:100,
  },
});

AppRegistry.registerComponent('CustomRnView', () => CustomRnView);
