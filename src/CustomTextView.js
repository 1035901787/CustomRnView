import React, {PureComponent,PropTypes} from 'react';
import {requireNativeComponent,View} from 'react-native';

const CustomTextView = {
    name:"CustomTextView",
    propTypes:{
        "text":PropTypes.string,
        "textSize":PropTypes.number,
        "textColor":PropTypes.number,
        ...View.propTypes
    }
}

const RCTCustomTextView = requireNativeComponent('CustomTextView',CustomTextView,{
    nativeOnly: {onChange: true}
});

export default class MyView extends PureComponent {

    _onChange = (event: Event) => {
        const onChangeMessage = this.props.onChangeMessage;
        onChangeMessage && onChangeMessage(event.nativeEvent);
    }

    render() {
        return (
            <RCTCustomTextView {...this.props} onChange={this._onChange}/>
        );
    }
}

MyView.propTypes = {
    onChangeMessage:PropTypes.func,
};