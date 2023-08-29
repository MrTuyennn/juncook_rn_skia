import {SafeAreaProvider} from 'react-native-safe-area-context';
import {Block, Text} from './components';
import {
  Dimensions,
  NativeModules,
  StyleProp,
  StyleSheet,
  TouchableOpacity,
  View,
  ViewStyle,
  requireNativeComponent,
} from 'react-native';
import {useTheme} from './theme';
import {NavigationContainer} from '@react-navigation/native';
import {useEffect, useState} from 'react';
interface ExoPlayerViewProps {
  style?: StyleProp<ViewStyle>;
  linkVideo?: string;
  onPrevVideo?: () => void;
  onNextVideo?: () => void;
  onChange?: (...args: any[]) => void;
}
const ExoPlayer = requireNativeComponent<ExoPlayerViewProps>('ExoPlayer');

const App = () => {
  const {NavigationTheme} = useTheme();

  const [count, setCount] = useState(0);

  const [linkVideo, setLinkVideo] = useState(
    'https://cdn-video.hanet.ai/hanet-camera-vn/video/upload/C22035C050/2023/08/21/2023-08-21-13-43-27.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=RWAMQB0V2PBI0C2AEUAB%2F20230821%2F%2Fs3%2Faws4_request&X-Amz-Date=20230821T071436Z&X-Amz-Expires=900&X-Amz-SignedHeaders=host&x-id=GetObject&X-Amz-Signature=eb9f5580f72f0d933705d8670864fa11154a042758244ab9ec93f975d6d92d64',
  );

  useEffect(() => {
    setTimeout(() => {
      setLinkVideo(
        'https://cdn-video.hanet.ai/hanet-camera-vn/video/upload/C22035C061/2023/08/28/2023-08-28-15-16-30.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=RWAMQB0V2PBI0C2AEUAB%2F20230828%2F%2Fs3%2Faws4_request&X-Amz-Date=20230828T081841Z&X-Amz-Expires=900&X-Amz-SignedHeaders=host&x-id=GetObject&X-Amz-Signature=7089e80384e3e1fa711596391534a0ab3ef8e462fbd418934f3f88904b231d37',
      );
    }, 5000);
  }, [linkVideo]);

  const increment = () => {
    NativeModules.Counter.increment((value: any) => {
      setCount(value);
    });
  };

  const decrement = async () => {
    try {
      var result = await NativeModules.Counter.decrement();
      setCount(result);
    } catch (e: any) {
      console.log(e.message, e.code);
    }
  };

  return (
    <NavigationContainer theme={NavigationTheme}>
      <SafeAreaProvider>
        <ExoPlayer
          onNextVideo={() => {
            console.log('onNextVideo');
          }}
          onPrevVideo={() => {
            console.log('onPrevVideo');
          }}
          onChange={event => {
            console.log('onChange', event);
          }}
          linkVideo={linkVideo}
          style={{
            height: (Dimensions.get('window').width * 9) / 16 + 120,
            width: Dimensions.get('window').width,
            backgroundColor: 'red',
          }}
        />
      </SafeAreaProvider>
    </NavigationContainer>
  );
};

export default App;

const styles = StyleSheet.create({
  buttonContainer: {
    height: 40,
    width: 100,
    backgroundColor: 'red',
    justifyContent: 'center',
    alignItems: 'center',
    marginVertical: 20,
  },
});
