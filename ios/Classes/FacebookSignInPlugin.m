#import "FacebookSignInPlugin.h"

@implementation FacebookSignInPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"facebook_sign_in"
            binaryMessenger:[registrar messenger]];
  FacebookSignInPlugin* instance = [[FacebookSignInPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"signInUser" isEqualToString:call.method]) {
    result(@"It works on ios");
  } else {
    result(FlutterMethodNotImplemented);
  }
}

@end
